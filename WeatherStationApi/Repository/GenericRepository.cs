using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace Repository
{
    public class GenericRepository<T> : IGenericRepository<T> where T : class
    {
        private DbContext _context;
        private DbSet<T> _table;

        public GenericRepository(DbContext context)
        {
            _context = context;
            _table = _context.Set<T>();
        }

        public async Task<T> Get(int id)
        {
            return await _table.FindAsync(id);
        }

        public async Task<IEnumerable<T>> GetAll()
        {
            return await _table.ToListAsync();
        }

        public async void Create(T obj)
        {
            await _table.AddAsync(obj);
        }
    }
}
