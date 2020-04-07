using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Entities;
using Models.MeasurementDto;


namespace WeatherStation
{
    public class AutoMapperProfile: Profile
    {
        public AutoMapperProfile()
        {
            //CreateMap<MeasurementForGetDto>(Measurement);
        }
    }
}
