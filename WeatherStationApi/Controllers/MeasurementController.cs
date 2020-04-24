using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Entities;
using Microsoft.AspNetCore.Mvc;
using Models.MeasurementDto;
using Services.MeasurementService;

namespace Controllers
{
    [Route("api/measurements")]
    public class MeasurementController: Controller
    {
        private IMeasurementService _measurementService;
        private IMapper _mapper;
        public MeasurementController(IMeasurementService measurementService, IMapper mapper)
        {
            _measurementService = measurementService;
            _mapper = mapper;
        }

        [HttpGet("measurement/latest")]
        public async Task<IActionResult> GetLatestMeasurement([FromQuery(Name = "type")] string type)
        {
            try
            {
                var measurement = await _measurementService.GetLatestMeasurementByType(type);
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

        [HttpPost("measurement/create", Name = "measurement")]
        public  async Task<IActionResult> Create([FromBody]MeasurementForCreationDto meas)
        {
            try
            {
                meas.DateTime = DateTime.Now;
                var measurementToPost = _mapper.Map<Measurement>(meas);
                var response = await _measurementService.Create(measurementToPost);

                if (!response)
                {
                    return StatusCode(500, "A problem happened with handling your request. Try again!");
                }

                return CreatedAtRoute("measurement", measurementToPost);
            }
            catch (Exception e)
            {
                return BadRequest("Faild to create");
            }
        }
    }
}
