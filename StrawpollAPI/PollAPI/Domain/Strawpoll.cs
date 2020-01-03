using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain
{
    public class Strawpoll
    {
        public int StrawpollId { get; set; }
        public String Question { get; set; }
        public DateTime DateCreated { get; set; }
        public IList<Answer> Answers { get; set; }
        public IList<VotedUUID> AlreadyVoted { get; set; }

        public Strawpoll(string question, DateTime dateCreated, IList<Answer> answers)
        {
            Question = question;
            DateCreated = dateCreated;
            Answers = answers;
            AlreadyVoted = new List<VotedUUID>();
        }

        private Strawpoll()
        {
            Answers = new List<Answer>();
            AlreadyVoted = new List<VotedUUID>();
        }

        public Strawpoll(string question, DateTime dateCreated, IList<Answer> answers, IList<VotedUUID> alreadyVoted) : this(question, dateCreated, answers)
        {
            AlreadyVoted = alreadyVoted;
        }
    }
}
