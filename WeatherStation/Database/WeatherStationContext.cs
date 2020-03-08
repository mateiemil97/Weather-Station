using System;
using Entities;
using Microsoft.EntityFrameworkCore;

namespace Database
{
    public class WeatherStationContext: DbContext
    {
        public WeatherStationContext(DbContextOptions<WeatherStationContext> options)
            : base(options) { }

        public DbSet<WeatherStation> WeatherStation { get; set; }
        public DbSet<Location> Location { get; set; }
        public DbSet<Measurement> Measurement { get; set; }
    }
}
