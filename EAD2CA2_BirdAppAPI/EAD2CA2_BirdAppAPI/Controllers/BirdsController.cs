using EAD2CA2_BirdAppAPI;
using EAD2CA2_BirdAppAPI.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EAD2CA2_BirdAppAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BirdsController : Controller
    {
        private readonly ProjectDbContext _dbContext;

        public BirdsController(ProjectDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Birds>> Get()
        {
            var birds = _dbContext.Birds.AsEnumerable();         // AsEnumerable if you're returning
                                                                // ToList if you'll do sth with it
            return Ok(birds);

        }

        [HttpPost]
        public ActionResult<string> Post(Birds newBird)
        {
            try
            {
                _dbContext.Birds.Add(newBird);
                _dbContext.SaveChanges();

                return Ok("Added Successfully");
            }
            catch (Exception)
            {
                return StatusCode(500, "Failed to add.");
            }
        }


        [HttpPut]
        public ActionResult<string> Put(Birds bird)
        {
            try
            {
                var birdToUpdate = _dbContext.Birds.FirstOrDefault(b => b.id == bird.id);
                if (birdToUpdate == null)
                    return NotFound("Bird not Found");

                birdToUpdate.commonName = bird.commonName;
                birdToUpdate.binomial = bird.binomial;
                birdToUpdate.irishName = bird.irishName;
                birdToUpdate.orderName = bird.orderName;
                birdToUpdate.familyName = bird.familyName;
                birdToUpdate.infoLink = bird.infoLink;
                birdToUpdate.liked = bird.liked;
                birdToUpdate.imageLink = bird.imageLink;

                _dbContext.SaveChanges();

                return Ok("Updated Successfully");
            }
            catch (Exception e)
            {
                return StatusCode(500, $"Failed to update, {e}");
            }
        }

        [HttpDelete]
        public ActionResult<string> Delete(int id)
        {
            try
            {
                var birdToDelete = _dbContext.Birds.FirstOrDefault(b => b.id == id);
                if (birdToDelete == null)
                    return NotFound("Bird not found");

                _dbContext.Birds.Remove(birdToDelete);
                _dbContext.SaveChanges();

                return Ok("Deleted Successfully");
            }
            catch (Exception)
            {
                return StatusCode(500, "Failed to Delete.");
            }
        }

    }
}
