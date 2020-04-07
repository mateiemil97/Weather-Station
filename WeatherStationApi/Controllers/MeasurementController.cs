using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Mvc;
using Services.MeasurementService;

namespace Controllers
{
    [Route("api/measurements")]
    public class MeasurementController: Controller
    {
        private IMeasurementService _measurementService;

        public MeasurementController(IMeasurementService measurementService)
        {
            _measurementService = measurementService;
        }

        [HttpGet("measurement/latest")]
        public IActionResult GetLatestMeasurement([FromQuery(Name = "type")] string type)
        {
            try
            {
                var measurement = _measurementService.GetLatestMeasurementByType(type);
                if (measurement != null)
                    return Ok(measurement);
                return NotFound();
            }
            catch (Exception e)
            {
                return BadRequest();
            }
        }
        [HttpGet()]
        public IActionResult GetMeasurementsByDay([FromQuery(Name = "type")]string type, [FromQuery(Name="date")]DateTime dateTime)
        {
            try
            {
                var measurements = _measurementService.GetMeasurementsByDay(type, dateTime);

                if (measurements == null)
                {
                    return NotFound("Data not found");
                }

                return Ok(measurements);
            }
            catch (Exception e)
            {
                return BadRequest();
            }
           
        }
    }
}
