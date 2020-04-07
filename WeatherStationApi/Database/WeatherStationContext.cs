using System;
using Entities;
using Microsoft.EntityFrameworkCore;

namespace Database
{
    public class WeatherStationContext: DbContext
    {
        public WeatherStationContext(DbContextOptions<WeatherStationContext> options)
            : base(options) { }

        public DbSet<Measurement> Measurement { get; set; }
    }
}
