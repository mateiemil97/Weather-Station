using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Models.WeatherStationModel;

namespace WeatherStation
{
    public class AutoMapperProfile: Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<Entities.WeatherStation, WeatherStationForGetDto>();
        }
    }
}
