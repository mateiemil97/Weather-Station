using System;
using System.Collections.Generic;
using System.Text;
using Entities;

namespace Services.MeasurementService
{
    public interface IMeasurementService
    {
        Measurement GetLatestMeasurementByType(string type);
        IEnumerable<Measurement> GetMeasurementsByDay(string type, DateTime date);
    }
}
