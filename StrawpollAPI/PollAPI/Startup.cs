using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using PollAPI.Data;
using PollAPI.Data.Repositories;
using PollAPI.Domain.IRepositories;

namespace PollAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<PollContext>(opts => opts.UseSqlServer(Configuration.GetConnectionString("DbContext")));

            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_1);

            services.AddScoped<DataInitializer>();
            services.AddScoped<IStrawpollRepository, StrawpollRepository>();
            services.AddScoped<IAnswerRepository, AnswerRepository>();
            services.AddScoped<IVotedUUIDRepository, VotedUUIDRepository>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, DataInitializer dataInitializer)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseMvc();

            dataInitializer.InitializeData();
        }
    }
}
