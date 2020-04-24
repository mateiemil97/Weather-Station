using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Repository
{
    public interface IGenericRepository<T> where T: class
    {
        Task<T> Get(int id);
        Task<IEnumerable<T>> GetAll();
        Task Create(T obj);
  
    }
}
