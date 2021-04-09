using EAD2CA2_BirdAppAPI;
using EAD2CA2_BirdAppAPI.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EAD2CA2_BirdAppAPI.Controllers
{
    //X00149064 MC
    [Route("api/[controller]")]
    [ApiController]
    public class BirdsController : Controller
    {
        private readonly ProjectDbContext _dbContext;
        private readonly Random _random = new Random();

        public BirdsController(ProjectDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        //X00149064 MC
        [HttpGet]
        public ActionResult<IEnumerable<Birds>> Get()
        {
            var birds = _dbContext.Birds.AsEnumerable();         // AsEnumerable if you're returning
                                                                // ToList if you'll do sth with it
            return Ok(birds);

        }

        //X00149064 MC
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

        //X00149064 MC
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

        //X00149064 MC
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

        //X00149064 MC
        [Route("familyName")]
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

        //X00149830 JF
        [Route("randomBird")]
        [HttpGet]
        public ActionResult<string> GetRandom()
        {
            try
            {
                int tableRows = _dbContext.Birds.Count();
                int randID = _random.Next(tableRows);

                var randomBirdToGet = _dbContext.Birds.Where(b => b.id == randID);

                return Ok(randomBirdToGet);

            }

            catch(Exception)
            {
                return StatusCode(500, "Random Bird could not be found");
            }
        }

        //X00149830 JF
        [Route("likedBirds")]
        [HttpGet]
        public ActionResult<string> GetLikedBirds()
        {

            try
            {
                var likedBirds = _dbContext.Birds.Where(b => b.liked != 0).AsEnumerable();
    
            return Ok(likedBirds);
            }
            catch
            {
                return StatusCode(500, "Random Bird could not be found");
            }
        }

        //X00149830 JF
        [Route("likeUnlike")]
        [HttpPut]
        public ActionResult<string> PutLikedUnliked(int? id)
        {
            {
                try
                {
                    var birdToLikeUnlike = _dbContext.Birds.FirstOrDefault(b => b.id == id);
                    if (birdToLikeUnlike == null)
                    {
                        return NotFound("Bird not found");
                    }
                    
                    // changing liked to 1 makes it true 
                    if(birdToLikeUnlike.liked == 0)
                    {
                        birdToLikeUnlike.liked = 1;
                        _dbContext.SaveChanges();
                        return Ok("bird was liked");
                    }
                    else
                    {
                        birdToLikeUnlike.liked = 0;
                        _dbContext.SaveChanges();
                        return Ok("bird was unliked");
                    }

                  
                }
                catch (Exception)
                {
                    return StatusCode(500, "Failed to Like/Unlike a bird");
                }
            }

        }
 


    }


}
