using PollAPI.Domain;
using PollAPI.Domain.IRepositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Repositories
{
    public class VotedUUIDRepository : IVotedUUIDRepository
    {
        private readonly PollContext _ctx;

        public VotedUUIDRepository(PollContext ctx)
        {
            _ctx = ctx;
        }

        public void Add(VotedUUID vote)
        {
            _ctx.Add(vote);
        }

        public IList<VotedUUID> GetAll()
        {
            return _ctx.VotedUUIDs
                .ToList();
        }

        public VotedUUID GetById(int id)
        {
            return _ctx.VotedUUIDs
                .FirstOrDefault(e => e.VotedUUIDId == id);
        }

        public void SaveChanges()
        {
            _ctx.SaveChanges();
        }

        public void Update(VotedUUID vote)
        {
            _ctx.VotedUUIDs.Update(vote);
        }
    }
}
