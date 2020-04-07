using Microsoft.EntityFrameworkCore.Migrations;

namespace WeatherStation.Migrations
{
    public partial class UpdateLocationTable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "City",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "Country",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "County",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "Comuna",
                table: "Location",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Nume",
                table: "Location",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Simplu",
                table: "Location",
                maxLength: 50,
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Comuna",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "Nume",
                table: "Location");

            migrationBuilder.DropColumn(
                name: "Simplu",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "City",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Country",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "County",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);
        }
    }
}
