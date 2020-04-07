using Microsoft.EntityFrameworkCore.Migrations;

namespace WeatherStation.Migrations
{
    public partial class UpdateLocationTable5 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Comuna",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "Simplu",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "Comun",
                table: "Location",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Simpl",
                table: "Location",
                maxLength: 50,
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Comun",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "Simpl",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "Comuna",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Simplu",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);
        }
    }
}
