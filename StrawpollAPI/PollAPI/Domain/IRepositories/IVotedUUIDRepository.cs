using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain.IRepositories
{
    public interface IVotedUUIDRepository
    {
        VotedUUID GetById(int id);
        IList<VotedUUID> GetAll();
        void Add(VotedUUID vote);
        void Update(VotedUUID vote);
        void SaveChanges();
    }
}
