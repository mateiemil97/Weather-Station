using System;
using System.Collections.Generic;
using System.Text;

namespace Models.MeasurementDto
{
    public class MeasurementForGetDto
    {
        public int Id { get; set; }
        public string Type { get; set; }
        public string Value { get; set; }
        public DateTime DateTime { get; set; }
    }
}
