using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Diagnostics.HealthChecks;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Diagnostics.HealthChecks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Finoban.Api {
    public class Startup {
        public Startup(IConfiguration configuration) {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services) {
            services.AddControllers();
            services.AddHealthChecks();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env) {
            if (env.IsDevelopment()) {
                app.UseDeveloperExceptionPage();
            }

            app.UseHealthChecks("/health-check", new HealthCheckOptions()
            {
                ResponseWriter = WriterResponse
            });

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints => {
                endpoints.MapControllers();
            });
        }
        
        private static Task WriterResponse(HttpContext httpContext, HealthReport result) {
            httpContext.Response.ContentType = "application/json";
            if (result.Status.ToString().Equals("Healthy"))
            {
                var json = new JObject(
                    new JProperty("statusHealthCkeck", "OK")
                );
                return httpContext.Response.WriteAsync(json.ToString(Formatting.Indented));   
            }
            else
            {
                var json = new JObject(
                    new JProperty("statusHealthCkeck", "NOK")
                );
                return httpContext.Response.WriteAsync(json.ToString(Formatting.Indented));   
            }
        }
    }
}
