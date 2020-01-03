using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain
{
    public class Answer
    {
        public int AnswerId { get; set; }
        public String AnswerString { get; set; }
        public int AmountVoted { get; set; }

        public Answer(string answerString, int amountVoted)
        {
            AnswerString = answerString;
            AmountVoted = amountVoted;
        }

        private Answer()
        {
        }
    }
}
