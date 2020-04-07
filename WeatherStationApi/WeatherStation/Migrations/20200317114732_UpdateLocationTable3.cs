using Microsoft.EntityFrameworkCore.Migrations;

namespace WeatherStation.Migrations
{
    public partial class UpdateLocationTable3 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Nume",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "Num",
                table: "Location",
                maxLength: 50,
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Num",
                table: "Location");

            migrationBuilder.AddColumn<string>(
                name: "Nume",
                table: "Location",
                type: "nvarchar(50)",
                maxLength: 50,
                nullable: true);
        }
    }
}
