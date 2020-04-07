
using System;
using System.Collections.Generic;
using System.Linq;
using Database;
using Entities;
using Models.MeasurementDto;

namespace Repository.MeasurementRepository
{
    public class MeasurementRepository: GenericRepository<Measurement>, IMeasurementRepository
    {
        private WeatherStationContext _context;
        public MeasurementRepository(WeatherStationContext context) : base(context)
        {
            _context = context;
        }

        public Measurement GetLatestValue(string type)
        {
            var measurementFromDb = (from measurement in _context.Measurement
                    where measurement.Type == type
                    orderby measurement.DateTime descending
                    select measurement
                ).Take(1).FirstOrDefault();

            return measurementFromDb;

        }

        public IEnumerable<Measurement> GetMeasurementsByDay(string type, DateTime date)
        {
            var measurement = (from meas in _context.Measurement
                where meas.Type == type
                      && meas.DateTime.DayOfYear == date.DayOfYear
                      && meas.DateTime.Month == date.Month
                      && meas.DateTime.Day == date.Day
                select meas).ToList();
            return measurement;
        }
    }
}
