using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using PollAPI.Domain;
using PollAPI.Domain.IRepositories;
using PollAPI.DTOs;

namespace PollAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class StrawpollController : ControllerBase
    {
        private readonly IStrawpollRepository _strawpollRepository;
        private readonly IAnswerRepository _answerRepository;
        private readonly IVotedUUIDRepository _votedRepository;

        public StrawpollController(IStrawpollRepository strawpollRepository, IAnswerRepository answerRepo, IVotedUUIDRepository votedRepo)
        {
            _strawpollRepository = strawpollRepository;
            _answerRepository = answerRepo;
            _votedRepository = votedRepo;
        }

        [HttpGet]
        public IEnumerable<Strawpoll> getStrawpolls()
        {
            return _strawpollRepository.GetAll();
        }

        [HttpGet("{id}")]
        public Strawpoll GetStrawpollById(int id)
        {
            return _strawpollRepository.GetById(id);
        }

        [HttpPost]
        public ActionResult<Strawpoll> PostStrawpoll(Strawpoll poll)
        {
            List<Answer> answers = new List<Answer>();
            poll.Answers.ToList().ForEach(e =>
            {
                Answer newAnswer = new Answer(e.AnswerString, e.AmountVoted);
                answers.Add(newAnswer);
                _answerRepository.Add(newAnswer);
            });

            Strawpoll pollDB = new Strawpoll(poll.Question, poll.DateCreated, answers);

            _strawpollRepository.Add(poll);
            _strawpollRepository.SaveChanges();

            return CreatedAtAction(nameof(GetStrawpollById), new { id = poll.StrawpollId }, poll);
        }

        [HttpPut("{id}")]
        public ActionResult<Strawpoll> PutStrawpoll(int id, Strawpoll poll)
        {
            if (id != poll.StrawpollId)
            {
                return BadRequest();
            }

            Strawpoll pollDB = _strawpollRepository.GetById(id);
            IList<VotedUUID> voted = new List<VotedUUID>();
            pollDB.DateCreated = poll.DateCreated;
            pollDB.Question = poll.Question;
            pollDB.StrawpollId = poll.StrawpollId;

            poll.Answers.ToList().ForEach(e =>
            {
                Answer a = _answerRepository.GetById(e.AnswerId);
                a.AmountVoted = e.AmountVoted;
                a.AnswerString = e.AnswerString;
                _answerRepository.Update(a);
            });

            poll.AlreadyVoted.ToList().ForEach(e =>
            {
                VotedUUID a = _votedRepository.GetById(e.VotedUUIDId);
                if(a != null)
                {
                    a.UUID = e.UUID;
                    _votedRepository.Update(a);
                }
                else
                {
                    a = new VotedUUID(e.UUID);
                    _votedRepository.Add(a);
                }
                voted.Add(a);
            });

            pollDB.AlreadyVoted = voted;

            _strawpollRepository.Update(pollDB);
            _strawpollRepository.SaveChanges();
            return CreatedAtAction(nameof(GetStrawpollById), new { id = poll.StrawpollId }, poll);
        }
    }
}