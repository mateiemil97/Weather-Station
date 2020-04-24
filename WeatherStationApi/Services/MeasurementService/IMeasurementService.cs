using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Entities;
using Models.MeasurementDto;

namespace Services.MeasurementService
{
    public interface IMeasurementService
    {
        Task<Measurement> GetLatestMeasurementByType(string type);
        IEnumerable<Measurement> GetMeasurementsByDay(string type, DateTime date);
        Task<bool> Create(Measurement measurement);
    }
}
