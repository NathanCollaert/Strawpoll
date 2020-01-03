using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Domain.IRepositories
{
    public interface IAnswerRepository
    {
        IList<Answer> GetByStrawpollId(int id);
        void Update(Answer answer);
        void SaveChanges();
        void Add(Answer answer);
        Answer GetById(int id);
    }
}
