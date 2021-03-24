using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EAD2CA2_BirdAppAPI.Controllers
{

    [Route("api/[controller]")]
    [ApiController]
    public class BirdFamilyController : Controller
    {
        private readonly ProjectDbContext _dbContext;

        public BirdFamilyController(ProjectDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpGet]
        public ActionResult<string> Get(string familyName)
        {
            try
            {
                var familyToGet = _dbContext.Birds.Where(b => b.familyName.Equals(familyName)).AsEnumerable();
                return Ok(familyToGet);
            }
            catch (Exception)
            {
                return StatusCode(500, "Failed to Delete.");
            }
        }
    }
}
