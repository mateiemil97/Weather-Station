using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Database;
using Entities;
using Microsoft.EntityFrameworkCore;
using Repository;
using Repository.MeasurementRepository;


namespace Services
{
    public class UnitOfWork: IUnitOfWork, IDisposable
    {
        private WeatherStationContext _context;
        
        public IMeasurementRepository Measurement { get; }
       
        public UnitOfWork(
            WeatherStationContext context,

            IMeasurementRepository measurement
          
            )
        {
            _context = context;
            Measurement = measurement;
        }

  
        public async Task<bool> SaveAsync()
        {
            return  await _context.SaveChangesAsync() >= 1;
        }

        private bool _disposed;

        protected virtual void Dispose(bool disposing)
        {
            if (!_disposed)
            {
                if (disposing)
                {
                    _context.Dispose();
                }
            }
            _disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}
