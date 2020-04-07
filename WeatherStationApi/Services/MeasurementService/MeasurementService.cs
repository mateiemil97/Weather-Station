using System;
using System.Collections.Generic;
using System.Text;
using Entities;

namespace Services.MeasurementService
{
    public class MeasurementService : IMeasurementService
    {
        private IUnitOfWork _unitOfWork;

        public MeasurementService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

         Measurement IMeasurementService.GetLatestMeasurementByType(string type)
        {
            var measurement = _unitOfWork.Measurement.GetLatestValue(type);
            return measurement;
        }

         IEnumerable<Measurement> IMeasurementService.GetMeasurementsByDay(string type, DateTime date)
         {
             var measurements = _unitOfWork.Measurement.GetMeasurementsByDay(type, date);
             return measurements;
         }
    }
}
