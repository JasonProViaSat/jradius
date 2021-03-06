/**
 * JRadius - A RADIUS Server Java Adapter
 * Copyright (C) 2004-2005 PicoPoint, B.V.
 * Copyright (c) 2006 David Bird <david@coova.com>
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package net.jradius.packet.attribute;


/**
 * The RADIUS VSA. All radius vendor specific attributes (as build by RadiusDictionary)
 * are derived from this abstract class.
 *
 * @author David Bird
 */
public abstract class VSAttribute extends RadiusAttribute
{
    private static final long serialVersionUID = 0L;

    protected long vendorId;
    protected long vsaAttributeType;

    protected short typeLength = 1;
    protected short lengthLength = 1;
    protected short extraLength = 0;
    protected boolean hasContinuationByte;
    protected short continuation;
    protected boolean grouped = false;
    
    public void setFormat(String format)
    {
    	String s[] = format.split(",");
    	if (s != null && s.length > 0)
    	{
    		typeLength = Short.parseShort(s[0]);
    		
    		if (s.length > 1) 
    		{
    			lengthLength = Short.parseShort(s[1]);
    		}
    		
    		if (s.length > 2)
    		{
    			if (s[2].equals("c")) 
    			{
    				hasContinuationByte = true;
    			}
    		}
    	}
    }
    
    /**
     * Returns the VSA type (lower 2 bytes) encoded with the Vendor ID 
     * (upper 2 bytes) as an integer.
     * @see net.jradius.packet.attribute.RadiusAttribute#getFormattedType()
     */
    public long getFormattedType()
    {
        return vsaAttributeType | (vendorId << 16);
    }

    /**
     * @return Returns the vendorId.
     */
    public long getVendorId()
    {
        return vendorId;
    }
    
    /**
     * @param vendorId The vendorId to set.
     */
    public void setVendorId(long vendorId)
    {
        this.vendorId = vendorId;
    }
    
    /**
     * @return Returns the vsaAttributeType.
     */
    public long getVsaAttributeType()
    {
        return vsaAttributeType;
    }
    
    /**
     * @param vsaAttributeType The vsaAttributeType to set.
     */
    public void setVsaAttributeType(long vsaAttributeType)
    {
        this.vsaAttributeType = vsaAttributeType;
    }


	public short getTypeLength() 
	{
		return typeLength;
	}

	public short getLengthLength()
	{
		return lengthLength;
	}

	public short getExtraLength() 
	{
		return extraLength;
	}

	public boolean hasContinuationByte() 
	{
		return hasContinuationByte;
	}

	public int getContinuation()
	{
		return continuation;
	}

	public void setContinuation(short cont) 
	{
		this.continuation = cont;
	}    

	public void setContinuation() 
	{
		setContinuation((short)(1 << 7));
	}

	public void unsetContinuation()
	{
		setContinuation((short)0);
	}

	public boolean isGrouped() 
	{
		return grouped;
	}

	public void setGrouped(boolean grouped) 
	{
		this.grouped = grouped;
	}    
}
