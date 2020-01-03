using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using PollAPI.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data.Mappers
{
    public class AnswerConfiguratie : IEntityTypeConfiguration<Answer>
    {
        public void Configure(EntityTypeBuilder<Answer> builder)
        {
            builder.ToTable("Answer");
        }
    }
}