using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using PollAPI.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Mappers
{
    public class VotedUUIDConfiguratie : IEntityTypeConfiguration<VotedUUID>
    {
        public void Configure(EntityTypeBuilder<VotedUUID> builder)
        {
            builder.ToTable("VotedUUID");
        }
    }
}
