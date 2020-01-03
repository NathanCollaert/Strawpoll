using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using PollAPI.Data.Mappers;
using PollAPI.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data
{
    public class PollContext: IdentityDbContext
    {
        public DbSet<Strawpoll> Strawpolls { get; set; }
        public DbSet<Answer> Answers { get; set; }
        public DbSet<VotedUUID> VotedUUIDs { get; set; }

        public PollContext(DbContextOptions<PollContext> opts) : base(opts)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.ApplyConfiguration(new StrawpollConfiguratie());
            modelBuilder.ApplyConfiguration(new AnswerConfiguratie());
            modelBuilder.ApplyConfiguration(new VotedUUIDConfiguratie());
        }
    }
}
