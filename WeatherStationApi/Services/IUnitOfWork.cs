using System;
using System.Threading.Tasks;
using Entities;
using Repository;
using Repository.MeasurementRepository;

namespace Services
{
    public interface IUnitOfWork
    {
        Task<bool> SaveAsync();
        IMeasurementRepository Measurement { get; }
       
    }
}
