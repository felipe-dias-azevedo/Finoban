FROM mcr.microsoft.com/dotnet/core/aspnet:3.1 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/core/sdk:3.1 AS build
WORKDIR /src
COPY ["Finoban.Api/Finoban.Api.Cifra.csproj", "Finoban.Api/"]
RUN dotnet restore "Finoban.Api/Finoban.Api.Cifra.csproj"
COPY . .
WORKDIR "/src/Finoban.Api"
RUN dotnet build "Finoban.Api.Cifra.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Finoban.Api.Cifra.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "Finoban.Api.Cifra.dll"]
