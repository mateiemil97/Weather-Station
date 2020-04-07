using System;
using System.Threading.Tasks;
using Entities;
using Repository;
using Repository.MeasurementRepository;

namespace Services
{
    public interface IUnitOfWork
    {
        void SaveAsync();
        
        IMeasurementRepository Measurement { get; }
       
    }
}
