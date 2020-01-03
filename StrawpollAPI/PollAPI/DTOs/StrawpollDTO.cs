using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.DTOs
{
    public class StrawpollDTO
    {
        public int StrawpollId { get; set; }
        public String Question { get; set; }
        public DateTime DateCreated { get; set; }
        public IList<AnswerDTO> Answers { get; set; }
        public IList<VotedUUIDDTO> AlreadyVoted { get; set; }
    }
}
