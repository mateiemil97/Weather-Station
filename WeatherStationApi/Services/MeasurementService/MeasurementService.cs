using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Entities;
using Models.MeasurementDto;

namespace Services.MeasurementService
{
    public class MeasurementService : IMeasurementService
    {
        private IUnitOfWork _unitOfWork;

        public MeasurementService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

         async Task<Measurement> IMeasurementService.GetLatestMeasurementByType(string type)
        {
            var measurement = await _unitOfWork.Measurement.GetLatestValue(type);
            return measurement;
        }

         IEnumerable<Measurement> IMeasurementService.GetMeasurementsByDay(string type, DateTime date)
         {
             var measurements = _unitOfWork.Measurement.GetMeasurementsByDay(type, date);
             return measurements;
         }

         async Task<bool> IMeasurementService.Create(Measurement measurement)
         {
             try
             {
                 await _unitOfWork.Measurement.Create(measurement);
                 var saved = await _unitOfWork.SaveAsync();
                 
                 if (!saved)
                 {
                     return false;
                 }

                 return true;
             }
             catch (Exception e)
             {
                 Console.WriteLine(e);
                 throw;
             }
             
         }
    }
}
