using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Entities;
using Microsoft.EntityFrameworkCore;
using Repository;
using Repository.MeasurementRepository;


namespace Services
{
    public class UnitOfWork: IUnitOfWork, IDisposable
    {
        private DbContext _context;
        
        public IMeasurementRepository Measurement { get; }
       
        public UnitOfWork(
            DbContext context,

            IMeasurementRepository measurement
          
            )
        {
            _context = context;
            Measurement = measurement;
        }

//        public GenericRepository<Location> Location
//        {
//            get
//            {
//                if(_location == null)
//                    _location = new GenericRepository<Location>(_context);
//                return _location;
//            }
//        }
//
//        public GenericRepository<Measurement> Measurement
//        {
//            get
//            {
//                if(_measurement == null)
//                    _measurement = new GenericRepository<Measurement>(_context);
//                return _measurement;
//            }
//        }
//
//        public GenericRepository<WeatherStation> WeatherStation
//        {
//            get
//            {
//                if (_weatherStation == null)
//                    _weatherStation = new GenericRepository<WeatherStation>(_context);
//                return _weatherStation;
//            }
//        }

        public void SaveAsync()
        {
            _context.SaveChangesAsync();
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
