﻿using Microsoft.EntityFrameworkCore.Migrations;

namespace WeatherStation.Migrations
{
    public partial class UpdateLocationTable61 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            

            migrationBuilder.DropColumn(
                name: "WeatherStationId",
                table: "Measurement");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "WeatherStationId",
                table: "Measurement",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.CreateTable(
                name: "WeatherStation",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    LocationId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_WeatherStation", x => x.Id);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Measurement_WeatherStationId",
                table: "Measurement",
                column: "WeatherStationId");

            migrationBuilder.AddForeignKey(
                name: "FK_Measurement_WeatherStation_WeatherStationId",
                table: "Measurement",
                column: "WeatherStationId",
                principalTable: "WeatherStation",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
