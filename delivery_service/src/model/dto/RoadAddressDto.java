package model.dto;

public class RoadAddressDto {
	private String zipCode = ""; // 우편번호
	private String roadAddress = ""; // 도로명 주소
	private String jibunAddress = ""; // 지번 주소
	private String detailAddress = ""; // 상세 주소

	public RoadAddressDto() {
	}

	public RoadAddressDto(String zipCode, String roadAddress, String jibunAddress, String detailAddress) {
		this.zipCode = zipCode;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.detailAddress = detailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@Override
	public String toString() {
		return "RoadAddressDto [zipCode=" + zipCode + ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress
				+ ", detailAddress=" + detailAddress + "]";
	}	
}
