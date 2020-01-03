using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using PollAPI.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Mappers
{
    public class StrawpollConfiguratie : IEntityTypeConfiguration<Strawpoll>
    {
        public void Configure(EntityTypeBuilder<Strawpoll> builder)
        {
            builder.ToTable("Strawpoll");
        }
    }
}
