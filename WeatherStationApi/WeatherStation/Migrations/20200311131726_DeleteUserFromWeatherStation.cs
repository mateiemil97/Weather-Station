using Microsoft.EntityFrameworkCore.Migrations;

namespace WeatherStation.Migrations
{
    public partial class DeleteUserFromWeatherStation : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "UserId",
                table: "WeatherStation");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "UserId",
                table: "WeatherStation",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }
    }
}
