using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain
{
    public class VotedUUID
    {
        public int VotedUUIDId { get; set; }
        public String UUID { get; set; }

        public VotedUUID(string uUID)
        {
            UUID = uUID;
        }

        private VotedUUID()
        {
        }
    }
}
