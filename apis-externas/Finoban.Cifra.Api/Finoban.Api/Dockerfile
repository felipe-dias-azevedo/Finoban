FROM mcr.microsoft.com/dotnet/core/sdk:3.1 AS build-env
WORKDIR /app
# Copiar csproj e restaurar dependencias
COPY *.csproj ./
RUN dotnet restore
RUN pwsh -Command Write-Host "Mvc31: Gerando imagem Docker e testando o PowerShell Core"
# Build da aplicacao
COPY . ./
RUN dotnet publish -c Release -o dist
# Build da imagem
FROM mcr.microsoft.com/dotnet/core/aspnet:3.1
WORKDIR /app
COPY --from=build-env /app/dist .
ENTRYPOINT ["dotnet", "Finoban.Cifra.Api.dll"]