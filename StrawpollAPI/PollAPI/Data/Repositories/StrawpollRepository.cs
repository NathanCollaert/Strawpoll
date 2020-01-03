using Microsoft.EntityFrameworkCore;
using PollAPI.Domain;
using PollAPI.Domain.IRepositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Repositories
{
    public class StrawpollRepository : IStrawpollRepository
    {
        private readonly PollContext _ctx;

        public StrawpollRepository(PollContext ctx)
        {
            _ctx = ctx;
        }

        public void Add(Strawpoll poll)
        {
            _ctx.Add(poll);
        }

        public IList<Strawpoll> GetAll()
        {
            return _ctx.Strawpolls
                .Include(e => e.Answers)
                .Include(e => e.AlreadyVoted)
                .ToList();
        }

        public Strawpoll GetById(int id)
        {
            return _ctx.Strawpolls
                .Include(e => e.Answers)
                .Include(e => e.AlreadyVoted)
                .FirstOrDefault(e => e.StrawpollId == id);
        }

        public void SaveChanges()
        {
            _ctx.SaveChanges();
        }

        public void Update(Strawpoll poll)
        {
            _ctx.Strawpolls.Update(poll);
        }
    }
}
