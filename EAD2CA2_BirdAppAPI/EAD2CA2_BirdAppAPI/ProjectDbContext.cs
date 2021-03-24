using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EAD2CA2_BirdAppAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace EAD2CA2_BirdAppAPI
{
    public class ProjectDbContext : DbContext
    {
        public DbSet<Birds> Birds { get; set; }

        public ProjectDbContext() : base() { }
        public ProjectDbContext(DbContextOptions<ProjectDbContext> options) : base(options) { }
    }
}
