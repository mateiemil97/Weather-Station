using System;
using System.Threading.Tasks;
using Entities;
using Repository;

namespace Services
{
    public interface IUnitOfWork
    {
        void Save();
        GenericRepository<Location> Location { get; }
        GenericRepository<Measurement> Measurement { get; }
        GenericRepository<WeatherStation> WeatherStation { get; }
    }
}
