using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain.IRepositories
{
    public interface IStrawpollRepository
    {
        Strawpoll GetById(int id);
        IList<Strawpoll> GetAll();
        void Add(Strawpoll poll);
        void Update(Strawpoll poll);
        void SaveChanges();
    }
}
