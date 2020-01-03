using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.DTOs
{
    public class AnswerDTO
    {
        public int AnswerId { get; set; }
        public String AnswerString { get; set; }
        public int AmountVoted { get; set; }
    }
}
