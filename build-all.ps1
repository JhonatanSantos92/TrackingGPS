Write-Host "Apagando stack Docker..."
docker-compose down -v

Write-Host "Build de imágenes Docker sin cache..."
docker-compose build --no-cache

Write-Host "Compilando proyectos Maven..."
$projects = Get-ChildItem -Directory

foreach ($p in $projects) {
    if (Test-Path "$($p.FullName)\pom.xml") {
        Write-Host "Compilando $($p.Name)..."
        Push-Location $p.FullName
        mvn clean package -DskipTests
        Pop-Location
    }
}

Write-Host "Levantando stack Docker..."
docker-compose up -d
