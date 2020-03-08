using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Entities;
using Models.WeatherStationModel;

namespace Services.WeatherStationService
{
    public class WeatherService: IWeatherStationService
    {
        private IUnitOfWork _unitOfWork;
        private IMapper _mapper;
        public WeatherService(IUnitOfWork unitOfWork, IMapper mapper)
        {
            _unitOfWork = unitOfWork;
            _mapper = mapper;
        }

        public async Task<IEnumerable<WeatherStation>> GetWeatherStations()
        {
            var stationsFromRepo = await _unitOfWork.WeatherStation.GetAll();
            return stationsFromRepo;
        }

        public Task<WeatherStationForGetDto> GetWeatherStation(int userId)
        {
            throw new NotImplementedException();
        }

        public void CreateWeatherStation(WeatherStationForCreationDto station)
        {
            throw new NotImplementedException();
        }
    }
}
