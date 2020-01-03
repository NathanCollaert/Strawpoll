using PollAPI.Domain;
using PollAPI.Domain.IRepositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Repositories
{
    public class AnswerRepository : IAnswerRepository
    {
        private readonly PollContext _ctx;

        public AnswerRepository(PollContext ctx)
        {
            _ctx = ctx;
        }

        public void Add(Answer answer)
        {
            _ctx.Add(answer);
        }

        public Answer GetById(int id)
        {
            return _ctx.Answers.FirstOrDefault(e => e.AnswerId == id);
        }

        public IList<Answer> GetByStrawpollId(int id)
        {
            return _ctx.Strawpolls.FirstOrDefault(e => e.StrawpollId == id).Answers.ToList();
        }

        public void SaveChanges()
        {
            _ctx.SaveChanges();
        }

        public void Update(Answer answer)
        {
            _ctx.Update(answer);
        }


    }
}
