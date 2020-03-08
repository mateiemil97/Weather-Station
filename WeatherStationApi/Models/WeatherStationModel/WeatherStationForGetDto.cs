using System;
using System.Collections.Generic;
using System.Text;

namespace Models.WeatherStationModel
{
    public class WeatherStationForGetDto
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int LocationId { get; set; }
    }
}
