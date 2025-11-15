# Script para ejecutar la aplicación con Docker Compose

Write-Host "=== Starting Franchise Application with Docker ===" -ForegroundColor Green
Write-Host ""

# Verificar si Docker está instalado
try {
    $dockerVersion = docker --version
    Write-Host "✓ Docker found: $dockerVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Docker is not installed or not in PATH" -ForegroundColor Red
    exit 1
}

# Verificar si Docker Compose está instalado
try {
    $composeVersion = docker-compose --version
    Write-Host "✓ Docker Compose found: $composeVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Docker Compose is not installed" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "=== Building and starting containers ===" -ForegroundColor Cyan
Write-Host "This may take several minutes the first time..." -ForegroundColor Yellow
Write-Host ""

docker-compose up --build

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "✗ Error starting containers" -ForegroundColor Red
    exit 1
}
