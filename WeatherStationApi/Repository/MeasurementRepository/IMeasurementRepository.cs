using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Entities;
using Models.MeasurementDto;
using Repository;

namespace Repository.MeasurementRepository
{
    public interface IMeasurementRepository: IGenericRepository<Measurement>
    {
        Task<Measurement> GetLatestValue(string type);
        IEnumerable<Measurement> GetMeasurementsByDay(string type, DateTime date);
    }
}
