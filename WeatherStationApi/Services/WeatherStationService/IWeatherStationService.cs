using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Entities;
using Models.WeatherStationModel;

namespace Services.WeatherStationService
{
    public interface IWeatherStationService
    {
        Task<IEnumerable<WeatherStation>> GetWeatherStations();
        Task<WeatherStationForGetDto> GetWeatherStation(int userId);
        void CreateWeatherStation(WeatherStationForCreationDto station);
    }
}
