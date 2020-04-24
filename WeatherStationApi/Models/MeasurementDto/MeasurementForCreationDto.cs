using System;
using System.Collections.Generic;
using System.Text;

namespace Models.MeasurementDto
{
    public class MeasurementForCreationDto
    {
        public string Type { get; set; }
        public float Value { get; set; }
        public DateTime DateTime { get; set; }
    }
}
